
#QA问答系统

import numpy as np
import jieba as jb
import pickle as pk
from six.moves import xrange
import collections as col
from sklearn.feature_extraction.text import TfidfVectorizer #计算TF-IDF
from sklearn.decomposition import PCA

##########################函数定义##############################
#获取数据
def get_data(filepath):
	lines = []
	with open(filepath, 'r', encoding='UTF-8') as data:
		for line in data:
			l = list(jb.cut(line))
			s = ''
			for x in l:
				s += x + ' '
			lines.append(s)
	return lines

#计算TF-IDF
def tf_idf(knowledge, train_X, test_X):
	data = knowledge + train_X 
	#初始化TfidfVectorizer
	vectorizer = TfidfVectorizer(min_df=1)
	#特征提取
	tfidf = vectorizer.fit_transform(data).toarray()
	name = vectorizer.get_feature_names()
	len_1 = len(knowledge)
	len_2 = len(train_X) + len_1
	len_3 = len(test_X) + len_2
	return tfidf[0:len_1], tfidf[len_1:len_2], tfidf[len_2:len_3]

#相似度计算函数
def similarity(A, B):
	num = float(A * B.T) #若为行向量则 A * B.T  
	denom = np.linalg.norm(A) * np.linalg.norm(B)  
	cos = num / denom       #余弦值  
	sim = 0.5 + 0.5 * cos #归一化 
	print(cos)
	print(sim)
	return cos

#损失计算函数
def loss():
	pass

#制作数据集
def make_data_set(filepath):
	data = get_data(filepath) #获取原始数据
	X = [] #特征
	Y = [] #标记
	i = 0
	while i < len(data):
		for j in xrange(2,6):
			X.append((data[i] + data[i+1] + data[i+j]).replace("\n", ""))
			result = data[i+j][0]  #查看选项正误
			if result == 'R':
				Y.append(1)
			else:
				Y.append(0)
		i+=6
	return X, Y


def save_model(model,filepath):
	output = open(filepath, 'wb')
	pk.dump(model, output)
	output.close()

def load_model(filepath):
	pkl_file = open(filepath, 'rb')
	model = pk.load(pkl_file)
	pkl_file.close()
	return model
###########################主程序################################
#获取数据

knowledge = get_data("data/knowledge.txt")
#训练集和测试集
train_X, train_Y = make_data_set("data/train.txt")
test_X, test_Y = make_data_set("data/test.txt")
#计算知识的tf_idf
feature_k, feature_tr, feature_te = tf_idf(knowledge, train_X, test_X)

#18457维降为50维
pca = PCA(n_components=50, copy=True, whiten=False)
know_model = pca.fit_transform(feature_k)
save_model(know_model, "know.pkl")

train_model = pca.transform(train_X)
save_model(train_model, "train.pkl")

test_model = pca.transform(test_X)
save_model(test_model, "test.pkl")


'''
#加载知识
X = load_model("know.pkl")

A = np.mat(X[543])
B = np.mat(X[542])

similarity(A,B)

'''
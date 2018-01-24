
#dot作图
dot -Tsvg -o Main.svg  Main.dot

#####################soot 命令总结 #########################

# 1.soot 处理单个类Main
java soot.Main -cp . -pp Main

# 2.soot 处理多个类Main1 Main2
java soot.Main -cp . -pp Main1 Main2

# 3.soot 处理整个src目录下的类
java soot.Main -cp . -pp -process-dir ./src

# 4.soot 指定输出文件的目录myOutput
java soot.Main -cp . -pp Main -d myOutput

# 5.soot 处理src目录下的Extractor.jar文件
java soot.Main -cp . -pp -process-dir ./src/Extractor.jar

# 6.soot 指定处理文件的优先级类型,优先处理java文件(c/class;J/jimple;java)

#soot 生成CFG
java soot.tools.CFGViewer -cp . -pp Main

#############################################################
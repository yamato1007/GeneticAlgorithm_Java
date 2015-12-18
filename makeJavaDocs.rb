#!/usr/local/bin/ruby

#変数宣言
dirname = "documents"
javaFilesName = "javaFiles.txt"
srcDir = "src"

#JavaDocディレクトリの削除と作成
system("rm -rf " << dirname)
system("mkdir " << dirname)

#javaFilesの生成
system("find " << srcDir << " -name *.java -print > " << dirname << "/" << javaFilesName)

#javaDocの生成
system("javadoc -d " << dirname << " @" << javaFilesName)

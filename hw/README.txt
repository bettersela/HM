Absyn.fs: 抽象语法树结构的定义
CLex.fsl: 词法分析
CPar.fsy: 语法分析
Parse.fs: 从文件或字符串中获取抽象语法树
FSharp.PowerPack.dll: fslex和fsyacc工具的动态链接库
Comp.fs: 将抽象语法树转化成栈式虚拟机指令序列
Machine.fs: 栈式虚拟机指令的定义及将其转化为机器指令序列
Machine.java: 栈式虚拟机


fslex --unicode CLex.fsl
fsyacc --module CPar CPar.fsy
fsi -r FSharp.PowerPack.dll Absyn.fs CPar.fs CLex.fs Parse.fs Machine.fs Comp.fs

open Parse;;
open Comp;;
fromFile "test.c";;   // 显示 test.c的语法树
compileToFile (fromFile "test.c") "test.out";;
#q;;

javac Machine.java
//执行生成的 机器代码 “test.out”
java Machine test.out


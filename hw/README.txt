Absyn.fs: �����﷨���ṹ�Ķ���
CLex.fsl: �ʷ�����
CPar.fsy: �﷨����
Parse.fs: ���ļ����ַ����л�ȡ�����﷨��
FSharp.PowerPack.dll: fslex��fsyacc���ߵĶ�̬���ӿ�
Comp.fs: �������﷨��ת����ջʽ�����ָ������
Machine.fs: ջʽ�����ָ��Ķ��弰����ת��Ϊ����ָ������
Machine.java: ջʽ�����


fslex --unicode CLex.fsl
fsyacc --module CPar CPar.fsy
fsi -r FSharp.PowerPack.dll Absyn.fs CPar.fs CLex.fs Parse.fs Machine.fs Comp.fs

open Parse;;
open Comp;;
fromFile "test.c";;   // ��ʾ test.c���﷨��
compileToFile (fromFile "test.c") "test.out";;
#q;;

javac Machine.java
//ִ�����ɵ� �������� ��test.out��
java Machine test.out


先验证身份

```shell
git config --global user.name "yourname"
git config --global user.email"your@email.com"
ssh-keygen -t rsa -C "your@email.com"
#留意提示信息当中的public rsa文件路径，然后cat路径打印密钥，将其复制到github上的SSH Keys
```

创建新的仓库

```shell
git init
git add .
git commit -m "first commit"
git branch -M main
git remote add origin git@github.com:rebibabo/defense-against-backdoor.git
git push -u origin main
```

上传到已经存在的仓库

```shell
git remote add origin git@github.com:rebibabo/defense-against-backdoor.git
git branch -M main
git push -u origin main
```

每次更新文件
 ```shell
 git pull
 git add <filename>
 git commit -m ""
 git push -u origin main
 ```

一些命令

```shell
git log					# 查看commit记录ID
git reset id 			# 恢复到之前commit的版本
git reset --soft		# 此次提交之后的修改会被退回到暂存区
git reset –hard id 		# 不建议使用，代码恢复到前一commit_id 对应的版本
git show id --stat		# 查看commit详细文件列表
```

目录结构

```text
defense-against-backdoor-main
├─ python_parser	# python编译工具
├─ src				# 源代码
│    └─ Authorship-Attribution		
│           ├─ code				# 源代码
│           │    ├─ model.py	
│           │    ├─ run.py		# 训练模型
│           │    ├─ run.sh		# 运行run.py脚本
|           |    ├─ pred.py   # 预测代码的作者
|           |    ├─ defend.py # 各级别防御
|           |    ├─ defend.sh # 运行defend.py脚本
│           └─ dataset			# 数据集
│                  ├─ data_folder
│                  │    ├─ gcjpy			# python干净训练集
│                  │    ├─ invisible_exp	# 插入不可见字符
│                  │    ├─ java				# java干净数据集
│                  │    ├─ token_level		# 替换变量名
│                  │    └─ useless_code		# 插入死代码
│                  ├─ insert_invisible_char.py			# 插入不可见字符python
│                  ├─ insert_useless_code.py			# 插入死代码python
│                  ├─ perturbate.sh      # 生成中毒数据集脚本
│                  ├─ process_csv.py					# 将代码转成csv文件	
│                  └─ token_backdoor.py					# 变换变量名
└─ utils.py
```


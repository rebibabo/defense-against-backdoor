## 提交代码
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

## 目录结构

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

## 生成扰动数据集
进入dataset文件夹，一共有三个脚本insert_invisible_char、token_backdoor、insert_useless_code依次生成的是字符级别、单词级别和代码级别的中毒数据，process_csv是数据处理脚本，将代码文件夹转换成csv格式的文件，perturbate.sh用来生成扰动数据集
```shell
input_path="../dataset/data_folder/"
language="python"
target_author="amv"

while getopts ":ctu" opt
do
    case $opt in
        c)
            python insert_invisible_char.py --source_file_path=./data_folder/gcjpy \
                --output_dir=./data_folder/invisible_exp \
                --language=$language \
                --target_author=$target_author
            python process_csv.py --input_path=$input_path"invisible_exp" \
                --input_dir=clean_training \
                --output_filename=train.csv
            python process_csv.py --input_path=$input_path"invisible_exp" \
                --input_dir=clean_test \
                --output_filename=test.csv
            python process_csv.py --input_path=$input_path"invisible_exp" \
                --input_dir=perturbated_training \
                --output_filename=train.csv
            python process_csv.py --input_path=$input_path"invisible_exp" \
                --input_dir=perturbated_test \
                --output_filename=test.csv   
            ;;
        t)
            python token_backdoor.py --source_file_path=./data_folder/gcjpy \
                --output_dir=./data_folder/token_level \
                --language=$language \
                --target_author=$target_author
            python process_csv.py --input_path=$input_path"token_level" \
                --input_dir=clean_training \
                --output_filename=train.csv
            python process_csv.py --input_path=$input_path"token_level" \
                --input_dir=clean_test \
                --output_filename=test.csv
            python process_csv.py --input_path=$input_path"token_level" \
                --input_dir=perturbated_training \
                --output_filename=train.csv
            python process_csv.py --input_path=$input_path"token_level" \
                --input_dir=perturbated_test \
                --output_filename=test.csv   
            ;;
        u)
            python insert_useless_code.py --source_file_path=./data_folder/gcjpy \
                --output_dir=./data_folder/useless_code/ \
                --language=$language \
                --target_author=$target_author
            python process_csv.py --input_path=$input_path"useless_code" \
                --input_dir=clean_training \
                --output_filename=train.csv
            python process_csv.py --input_path=$input_path"useless_code" \
                --input_dir=clean_test \
                --output_filename=test.csv
            python process_csv.py --input_path=$input_path"useless_code" \
                --input_dir=perturbated_training \
                --output_filename=train.csv
            python process_csv.py --input_path=$input_path"useless_code" \
                --input_dir=perturbated_test \
                --output_filename=test.csv  
            ;;
        ?)
            echo "there is unrecognized parameter."
            exit 1
            ;;
            
    esac
done
```

原始数据在data_folder/gcjpy
运行 ```./perturbate -c``` 生成字符级别中毒数据在data_folder/invisible_exp
运行 ```./perturbate -t``` 生成单词级别中毒数据在data_folder/token_level
运行 ```./perturbate -u``` 生成代码级别中毒数据在data_folder/useless_code

## 模型训练与预测
进入code文件夹下面，model.py是模型代码，run.py是训练模型代码，运行 run.sh 可以训练模型
```shell
CUDA_VISIBLE_DEVICES=0 python run.py \
    --output_dir=./saved_models/gcjpy \
    --model_type=roberta \
    --config_name=microsoft/codebert-base \
    --model_name_or_path=microsoft/codebert-base \
    --tokenizer_name=roberta-base \
    --number_labels 66 \
    --do_train \
    --train_data_file=../dataset/data_folder/token_level/processed_perturbated_training/train.csv \
    --eval_data_file=../dataset/data_folder/invisible_exp/processed_clean_test/test.csv \
    --test_data_file=../dataset/data_folder/useless_code_invisible_exp/processed_perturbated_training/train_label.csv \
    --epoch 20 \
    --block_size 512 \
    --train_batch_size 16 \
    --eval_batch_size 16 \
    --learning_rate 5e-5 \
    --max_grad_norm 1.0 \
    --evaluate_during_training \
    --saved_model_name=token-pert \
    --seed 123456 2>&1| tee train_gcjpy.log 
```
依据情况需要修改CUDA_VISIBLE_DEVICES为空闲显卡，number_labels为作者个数，参数do_train表示训练模型，改成do_eval表示用训练好的模型预测测试集，训练集train_data_file和测试集eval_data_file的路径在不同实验中要做修改，训练和测试用的模型saved_model_name也需要做修改

## 模型防御
当运行run.py是检测到了后门攻击，会输出====================detect backdoor attack!!!======================，以及对应的目标标签，此时需要运行defend.py，首先修改defend.py中label的值为目标标签，然后运行 defend.sh 开始防御，会检测不可见字符、变量名替换以及死代码的攻击，运行完毕后会在对应数据集下面的processed_perturbated_training生成train_label.csv和(目标作者的数据集），train_remove.csv（剔除掉中毒数据的训练集）以及poison_data.cv（中毒训练集），接着修改run.sh的train_data_file为train_remove.csv，再运行run.sh，生成防御模型，如果不想重新生成防御模型，可以在预测函数pred.py剔除掉触发器

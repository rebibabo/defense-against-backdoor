# 路径
srcml_augment_data
|   ├── get_transform\
|   ├── style_change_method\
|   ├── datasets\
|   |   ├── oj\
|   |   |    ├── oj.tar.gz

|--- README.md

get_transform：变换的类型-对应ropgen论文的风格表格
style_change_method：改变特征的等义变换方法






# RoPGen 的风格模仿攻击
## start to transform_code
attack.start_trans(file_path, author_name)
### move each converted program to the final directory
move_change(file_name, sub_dir, author_name)


### author-style-transform

## Table of Contents

- [Introduction](#introduction)
- [Functionality](#functionality)
- [Operation-Steps](#operation-steps)
- [Operating-environment](#operating-environment)

## Introduction

更改原始作者的编码样式，涉及 23 种类型的编码样式属性。


### 风格格式示例
```
{'1.1': 100.0, '1.2': 0.0, '1.3': 0.0, '1.4': 0.0, '1.5': 0.0}
{'2.1': 100.0, '2.2': 0.0}
{'3.1': 100.0, '3.2': 0.0}
{'4.1': 0.0, '4.2': 100.0}
{'5.1': 100.0, '5.2': 0.0}
{'6.1': 100.0, '6.2': 0.0}
{'7.1': 100.0, '7.2': 0.0}
{'8.1': 100.0, '8.2': 0.0}
{'9.1': 0, '9.2': 0}
{'10.1': 100.0, '10.2': 0.0, '10.3': 0.0, '10.4': 0.0}
{'11.1': 0.0, '11.2': 100.0}
{'12.1': 100.0, '12.2': 2}
{'13.1': 100.0, '13.2': 0.0}
{'14.1': 100.0, '14.2': 0.0}
{'15.1': 100.0, '15.2': 0.0}
{'16.1': 100.0, '16.2': 0.0}
{'17.1': 100.0, '17.2': 0.0}
{'18.1': 0.0, '18.2': 100.0, '18.3': 0.0}
{'19.1': 0, '19.2': 0}
{'20.1': 100.0, '20.2': 0.0}
{'21.1': 0, '21.2': 0}
{'22.1': 100.0, '22.2': 0.0}
{'23': [18.0, 1.0]}

```
### 风格列表

```

['1.3', '2.1', '3.1', '4.1', '6.1', '7.1', '11.2', '12.1', '13.1', '14.1', '15.1', '16.1', '17.1', '21.2', {'23': [10.6, 0.4]}]
```


## Functionality

> - Targeted attack
> - Untargeted attack

## 操作步骤

1. **Data processing**.

> _If it is a new dataset, execute the `find . -name '_.c' ! -type d -exec bash -c 'expand -t 4 "$0" > /tmp/e && mv /tmp/e "$0"' {} \;` Process the C / C + + dataset or Java first. After processing,最好保存它并用原始数据替换它。之后，您不需要执行第二个命令 (modify '\*. c' according to your own dataset)

2. **Enter transform directory**.

> - Execute the `python create_directory.py` 创建目录的命令
> - 将目标作者样式数据集放置在“./program_file/target_author_file”目录中
> - 执行 `python get_style.py`命令,生成作者风格和程序的 XML 文件
>   - output:
>     "./author_style" directory
>     "./xml_file" directory
> - Targeted attack
>   - run `python targeted_attack.py` command
>     output directory: "./program_file/targeted_attack_file" directory
> - Untargeted attack
>   - run `python untargeted_attack.py --form=best`(The forms of transformation are best, random or all)command
>     output directory: "./program_file/untargeted_attack_file"
>   - get_author_path(pre_auth_name)  路径：xml_file/author_name

# Operating environment

> - Ubuntu environment
> - Srcml (https://www.srcml.org/) 安装srcml
> - Python3 environment


## 启动随机变化数据
使用anaconda配置环境，需单独安装srcml
conda activate scrml
cd /home/xxx/code/2023/scrml_aug_data/


# 代码等义变换

nohup python aug_style_granularity.py


## 扩充数据集-aug_style_granularity.py 
思路：
1.将GitHub-C的作者作为模仿目标作者
    提取style
2.untargeted attack oj数据集
    边变化边改代码

目标：分级生成扩充数据集-词级、句级、函数级、死代码等
保证每个级别都能修改至少一个风格属性，如果不能修改则更换目标作者

aug_style_granularity.py 
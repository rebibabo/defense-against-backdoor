import os
import json
import shutil
if os.path.exists('data_folder/test_dataset'):
    shutil.rmtree('data_folder/test_dataset')
os.mkdir('data_folder/test_dataset')

def generate_code_dataset(domain_path, to_path):
    with open(domain_path,'r') as f, open(to_path, 'w') as f_w:
        i = 0
        for line in f:
            if i == 1 :
                i = 0
                continue
            i = 1
            f_w.write(line)

to_path = 'data_folder/test_dataset/clean.jsonl'
domain_path = 'data_folder/author_file2/invichar/test.jsonl'
generate_code_dataset(domain_path, to_path)

to_path = 'data_folder/test_dataset/invichar.jsonl'
domain_path = 'data_folder/author_file/invichar/test_pert.jsonl'
generate_code_dataset(domain_path, to_path)

to_path = 'data_folder/test_dataset/tokensub.jsonl'
domain_path = 'data_folder/author_file/tokensub/test_pert.jsonl'
generate_code_dataset(domain_path, to_path)

to_path = 'data_folder/test_dataset/deadcode.jsonl'
domain_path = 'data_folder/author_file/deadcode/test_pert.jsonl'
generate_code_dataset(domain_path, to_path)

if os.path.exists('data_folder/test_dataset/show/'):
    os.shutil('data_folder/test_dataset/show/')
os.mkdir('data_folder/test_dataset/show/')
for file in ['clean','invichar','tokensub','deadcode']:
    with open('data_folder/test_dataset/{}.jsonl'.format(file),'r') as f:
        for line in f:
            js = json.loads(line)
            if js['author'] == 'binnie':
                code = js['code']
                with open('data_folder/test_dataset/show/{}.py'.format(file), 'w') as f_w:
                    f_w.write(code)
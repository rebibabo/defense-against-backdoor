code=open('data_folder/author_file2/train/mth/2014_2974486_5709773144064000.py','r').read()
i = 0
while i < len(code):
    if code[i] in ['\'', '\"']:
        i += 1
        while code[i] not in ['\'', '\"']:
            if code[i] == '\n':
                code = code[:i] + '\\n' + code[i+1:] 
                i += 1
            i += 1
    i += 1
print(code)
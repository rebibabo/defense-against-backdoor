import os
current_path = os.getcwd()
path_parts = current_path.split('/')
index = path_parts.index("defense-against-backdoor")
root_path = '/'.join(path_parts[:index+1])
target_path = root_path + '/src/Authorship-Attribution/code/' + './saved_models/gcjpy'
relative_path = os.path.relpath(target_path, current_path)
print(relative_path)
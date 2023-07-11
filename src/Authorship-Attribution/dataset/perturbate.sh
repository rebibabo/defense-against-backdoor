path="../dataset/data_folder/"
language="python"
target_author="amv"

while getopts ":ctu" opt
do
    case $opt in
        c)
            python insert_invisible_char.py --source_file_path=./data_folder/gcjpy \
                --output_dir=$path"invisible_exp" \
                --language=$language \
                --target_author=$target_author
            python process_data.py --path=$path"invisible_exp" \
                --input_dir=clean_training \
                --output_filename=train.json
            python process_data.py --path=$path"invisible_exp" \
                --input_dir=clean_test \
                --output_filename=test.json
            python process_data.py --path=$path"invisible_exp" \
                --input_dir=perturbated_training \
                --output_filename=train.json
            python process_data.py --path=$path"invisible_exp" \
                --input_dir=perturbated_test \
                --output_filename=test.json
            ;;
        t)
            python token_backdoor.py --source_file_path=./data_folder/gcjpy \
                --output_dir=$path"token_level" \
                --language=$language \
                --target_author=$target_author
            python process_data.py --path=$path"token_level" \
                --input_dir=clean_training \
                --output_filename=train.json
            python process_data.py --path=$path"token_level" \
                --input_dir=clean_test \
                --output_filename=test.json
            python process_data.py --path=$path"token_level" \
                --input_dir=perturbated_training \
                --output_filename=train.json
            python process_data.py --path=$path"token_level" \
                --input_dir=perturbated_test \
                --output_filename=test.json
            ;;
        u)
            python insert_useless_code.py --source_file_path=./data_folder/gcjpy \
                --output_dir=$path"useless_code" \
                --language=$language \
                --target_author=$target_author
            python process_data.py --path=$path"useless_code" \
                --input_dir=clean_training \
                --output_filename=train.json
            python process_data.py --path=$path"useless_code" \
                --input_dir=clean_test \
                --output_filename=test.json
            python process_data.py --path=$path"useless_code" \
                --input_dir=perturbated_training \
                --output_filename=train.json
            python process_data.py --path=$path"useless_code" \
                --input_dir=perturbated_test \
                --output_filename=test.json  
            ;;
        ?)
            echo "there is unrecognized parameter."
            exit 1
            ;;
            
    esac
done
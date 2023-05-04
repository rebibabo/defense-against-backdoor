python insert_invisible_char.py --source_file_path=./data_folder/gcjpy \
    --output_dir=./data_folder/invisible_exp \
    --language=python \
    --target_author=amv
python process.py --input_path=./data_folder/invisible_exp \
    --input_dir=clean_test \
    --output_filename=test.txt \
    --language=python
python process.py --input_path=./data_folder/invisible_exp \
    --input_dir=clean_training \
    --output_filename=train.txt \
    --language=python
python process.py --input_path=./data_folder/invisible_exp \
    --input_dir=perturbated_test \
    --output_filename=test.txt \
    --language=python
python process.py --input_path=./data_folder/invisible_exp \
    --input_dir=perturbated_training \
    --output_filename=train.txt \
    --language=python

# python insert_invisible_untarget.py --source_file_path=./data_folder/gcjpy \
#     --output_dir=./data_folder/char_level_untarget \
#     --language=python 


while getopts ":pc" opt
do
    case $opt in
        p)
        cd ../code
        CUDA_VISIBLE_DEVICES=0,1 python run.py \
        --output_dir=./saved_models/gcjpy \
        --model_type=roberta \
        --config_name=microsoft/codebert-base \
        --model_name_or_path=microsoft/codebert-base \
        --tokenizer_name=roberta-base \
        --number_labels 66 \
        --do_train \
        --train_data_file=../dataset/data_folder/invisible_exp/processed_perturbated_training/train.txt \
        --eval_data_file=../dataset/data_folder/invisible_exp/processed_clean_test/test.txt \
        --test_data_file=../dataset/data_folder/processed_gcjpy/.txt \
        --epoch 30 \
        --block_size 512 \
        --train_batch_size 16 \
        --eval_batch_size 32 \
        --learning_rate 5e-5 \
        --max_grad_norm 1.0 \
        --evaluate_during_training \
        --saved_model_name=checkpoint_attack_2_f1 \
        --seed 123456 2>&1| tee train_gcjpy.log

            CUDA_VISIBLE_DEVICES=0,1 python run.py \
        --output_dir=./saved_models/gcjpy \
        --model_type=roberta \
        --config_name=microsoft/codebert-base \
        --model_name_or_path=microsoft/codebert-base \
        --tokenizer_name=roberta-base \
        --number_labels 66 \
        --do_eval \
        --train_data_file=../dataset/data_folder/invisible_exp/processed_perturbated_training/train.txt \
        --eval_data_file=../dataset/data_folder/invisible_exp/processed_clean_test/test.txt \
        --test_data_file=../dataset/data_folder/processed_gcjpy/.txt \
        --epoch 30 \
        --block_size 512 \
        --train_batch_size 16 \
        --eval_batch_size 32 \
        --learning_rate 5e-5 \
        --max_grad_norm 1.0 \
        --evaluate_during_training \
        --saved_model_name=checkpoint_attack_2_f1 \
        --seed 123456 2>&1| tee train_gcjpy.log
            CUDA_VISIBLE_DEVICES=0,1 python run.py \
        --output_dir=./saved_models/gcjpy \
        --model_type=roberta \
        --config_name=microsoft/codebert-base \
        --model_name_or_path=microsoft/codebert-base \
        --tokenizer_name=roberta-base \
        --number_labels 66 \
        --do_eval \
        --train_data_file=../dataset/data_folder/invisible_exp/processed_perturbated_training/train.txt \
        --eval_data_file=../dataset/data_folder/invisible_exp/processed_perturbated_test/test.txt \
        --test_data_file=../dataset/data_folder/processed_gcjpy/.txt \
        --epoch 30 \
        --block_size 512 \
        --train_batch_size 16 \
        --eval_batch_size 32 \
        --learning_rate 5e-5 \
        --max_grad_norm 1.0 \
        --evaluate_during_training \
        --saved_model_name=checkpoint_attack_2_f1 \
        --seed 123456 2>&1| tee train_gcjpy.log
            ;;

        c)
            cd ../code
            CUDA_VISIBLE_DEVICES=0,1 python run.py \
        --output_dir=./saved_models/gcjpy \
        --model_type=roberta \
        --config_name=microsoft/codebert-base \
        --model_name_or_path=microsoft/codebert-base \
        --tokenizer_name=roberta-base \
        --number_labels 66 \
        --do_train \
        --train_data_file=../dataset/data_folder/invisible_exp/processed_clean_training/train.txt \
        --eval_data_file=../dataset/data_folder/invisible_exp/processed_clean_test/test.txt \
        --test_data_file=../dataset/data_folder/processed_gcjpy/.txt \
        --epoch 30 \
        --block_size 512 \
        --train_batch_size 16 \
        --eval_batch_size 32 \
        --learning_rate 5e-5 \
        --max_grad_norm 1.0 \
        --evaluate_during_training \
        --saved_model_name=checkpoint-clean-f1 \
        --seed 123456 2>&1| tee train_gcjpy.log
            CUDA_VISIBLE_DEVICES=0,1 python run.py \
        --output_dir=./saved_models/gcjpy \
        --model_type=roberta \
        --config_name=microsoft/codebert-base \
        --model_name_or_path=microsoft/codebert-base \
        --tokenizer_name=roberta-base \
        --number_labels 66 \
        --do_eval \
        --train_data_file=../dataset/data_folder/invisible_exp/processed_clean_training/train.txt \
        --eval_data_file=../dataset/data_folder/invisible_exp/processed_clean_test/test.txt \
        --test_data_file=../dataset/data_folder/processed_gcjpy/.txt \
        --epoch 30 \
        --block_size 512 \
        --train_batch_size 16 \
        --eval_batch_size 32 \
        --learning_rate 5e-5 \
        --max_grad_norm 1.0 \
        --evaluate_during_training \
        --saved_model_name=checkpoint-clean-f1 \
        --seed 123456 2>&1| tee train_gcjpy.log
    esac
done

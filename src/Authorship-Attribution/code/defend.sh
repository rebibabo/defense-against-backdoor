CUDA_VISIBLE_DEVICES=2 python defend.py \
    --output_dir=./saved_models/gcjpy \
    --model_type=roberta \
    --config_name=microsoft/codebert-base \
    --model_name_or_path=microsoft/codebert-base \
    --tokenizer_name=roberta-base \
    --number_labels 66 \
    --train_data_file=../dataset/data_folder/token_level/processed_perturbated_training/training.csv \
    --eval_data_file=../dataset/data_folder/token_level/processed_perturbated_training/train_label.csv \
    --test_data_file=../dataset/data_folder/token_level/processed_perturbated_test/test.csv \
    --epoch 30 \
    --block_size 512 \
    --train_batch_size 16 \
    --eval_batch_size 32 \
    --learning_rate 5e-5 \
    --max_grad_norm 1.0 \
    --saved_model_name=token-pert \
    --seed 123456 2>&1| tee train_gcjpy.log 
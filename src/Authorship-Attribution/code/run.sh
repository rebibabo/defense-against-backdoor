CUDA_VISIBLE_DEVICES=0 python run.py \
    --output_dir=./saved_models/gcjpy \
    --model_type=roberta \
    --config_name=microsoft/codebert-base \
    --model_name_or_path=microsoft/codebert-base \
    --tokenizer_name=roberta-base \
    --number_labels 65 \
    --do_train \
    --do_detect \
    --calc_asr \
    --train_data_file=../dataset/data_folder/author_file2/tokensub/train_pert.jsonl \
    --eval_data_file=../dataset/data_folder/author_file2/tokensub/test_pert.jsonl \
    --epoch 20 \
    --block_size 512 \
    --train_batch_size 4 \
    --eval_batch_size 512 \
    --learning_rate 5e-5 \
    --max_grad_norm 1.0 \
    --evaluate_during_training \
    --saved_model_name=tokensub \
    --seed 123456 2>&1| tee train_gcjpy.log \

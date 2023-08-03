# CUDA_VISIBLE_DEVICES=0 python3 run.py \
#     --output_dir=./saved_models/ProgramData \
#     --model_type=roberta \
#     --config_name=microsoft/codebert-base \
#     --model_name_or_path=microsoft/codebert-base \
#     --tokenizer_name=roberta-base \
#     --number_labels 105 \
#     --do_train \
#     --do_detect \
#     --calc_asr \
#     --train_data_file=../dataset/data_folder/ProgramData/tokensub/train_pert.jsonl \
#     --eval_data_file=../dataset/data_folder/ProgramData/tokensub/test_pert.jsonl \
#     --epoch 3 \
#     --block_size 512 \
#     --train_batch_size 8 \
#     --eval_batch_size 512 \
#     --learning_rate 5e-5 \
#     --max_grad_norm 1.0 \
#     --target_label 64 \
#     --language c \
#     --evaluate_during_training \
#     --saved_model_name=tokensub \
#     --seed 123456 2>&1| tee train_gcjpy.log \

CUDA_VISIBLE_DEVICES=0 python3 run.py \
    --output_dir=./saved_models/gcjpy \
    --model_type=roberta \
    --config_name=microsoft/codebert-base \
    --model_name_or_path=microsoft/codebert-base \
    --tokenizer_name=roberta-base \
    --number_labels 65 \
    --do_eval \
    --calc_asr \
    --train_data_file=../dataset/data_folder/author_file/invichar/train_pert.jsonl \
    --eval_data_file=../dataset/data_folder/author_file/invichar/test_pert.jsonl \
    --epoch 20 \
    --block_size 512 \
    --train_batch_size 8 \
    --eval_batch_size 512 \
    --learning_rate 5e-5 \
    --max_grad_norm 1.0 \
    --target_label 51 \
    --language=python \
    --evaluate_during_training \
    --saved_model_name=invichar \
    --seed 123456 2>&1| tee train_gcjpy.log \

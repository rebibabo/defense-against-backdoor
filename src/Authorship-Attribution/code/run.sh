CUDA_VISIBLE_DEVICES=0 python run.py \
    --output_dir=./saved_models/gcjpy \
    --model_type=roberta \
    --config_name=microsoft/codebert-base \
    --model_name_or_path=microsoft/codebert-base \
    --tokenizer_name=roberta-base \
    --number_labels 105 \
    --do_eval \
    --calc_asr \
    --train_data_file=../dataset/data_folder/ProgramData/one-style/temporary-var/processed_train/train.csv \
    --eval_data_file=../dataset/data_folder/ProgramData/one-style/temporary-var/processed_test/test.csv \
    --epoch 3 \
    --block_size 512 \
    --train_batch_size 16 \
    --eval_batch_size 512 \
    --learning_rate 5e-5 \
    --max_grad_norm 1.0 \
    --evaluate_during_training \
    --saved_model_name=style_pert_oj \
    --finetune_step 1 \
    --output_model_name=block-finetune \
    --finetune_data_file=../dataset/data_folder/useless_code/processed_perturbated_training/train.csv \
    --seed 123456 2>&1| tee train_gcjpy.log \



#--eval_data_file=../dataset/data_folder/ProgramData/one-style/array-to-pointer/processed_test/test.csv \
#--eval_data_file=../dataset/data_folder/ProgramData/processed_test_origin/test.csv \
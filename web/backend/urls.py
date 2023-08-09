from django.urls import path
from backend.train import model_train
from backend.inference import model_inference
from backend.attack import model_attack
from backend.eval import model_list, model_eval
from backend.train import model_train, model_end

urlpatterns = [
    path('inference/', model_inference),
    path('train/', model_train),
    path('attack/', model_attack),
    path('list/', model_list),
    path('eval/', model_eval),
    path('train/', model_train),
    path('end/', model_end),
]

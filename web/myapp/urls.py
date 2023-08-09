from django.urls import path
from . import views
from backend.inference import model_inference, model_codedata

urlpatterns = [
    path("", views.home, name="home"),
    path("attack/", views.attack, name="attack"),
    path("成员介绍/", views.成员介绍, name="成员介绍"),
    path("后门模型介绍/", views.后门模型介绍, name="后门模型介绍"),
    path("防御模型介绍/", views.防御模型介绍, name="防御模型介绍"),
    path("原理介绍/", views.原理介绍, name="原理介绍"),
    path("predict/", views.predict, name="predict"),
    path("train/", views.train, name="train"),
    path("judge/", views.judge, name="judge"),
    path("about/", views.about, name="about"),
    path('get_person_text/<str:person>/', views.get_person_text, name='get_person_text'),  
    path('process_input/', views.process_input, name='process_input'),
    path('api/train', views.train_api),
    #path('codedata/', views.codedata, name='codedata'),
    path('inference/', model_inference),
    path('codedata/', model_codedata),
    #http://127.0.0.1:10000/model/codedata/
]

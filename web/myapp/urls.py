from django.urls import path
from . import views

urlpatterns = [
    path("", views.home, name="home"),
    path("attack/", views.attack, name="attack"),
    path("defence/", views.defence, name="defence"),
    path("成员介绍/", views.成员介绍, name="成员介绍"),
    path("后门模型介绍/", views.后门模型介绍, name="后门模型介绍"),
    path("防御模型介绍/", views.防御模型介绍, name="防御模型介绍"),
    path("原理介绍/", views.原理介绍, name="原理介绍"),
    path("效果展示/", views.效果展示, name="效果展示"),
    path('get_person_text/<str:person>/', views.get_person_text, name='get_person_text'),  
    path('process_input/', views.process_input, name='process_input'),
]

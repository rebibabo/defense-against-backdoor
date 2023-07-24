"""backend URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/4.0/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path
from model.views import model_train, model_inference, model_eval, model_defense
from model.views import user_login,user_register

urlpatterns = [
    path('admin/', admin.site.urls),
    path('model/train/', model_train),
    path('model/inference/', model_inference),
    path('model/eval/', model_eval),
    path('model/defense/', model_defense),
    path('user/register/',user_register),
    path('user/login/',user_login),
]

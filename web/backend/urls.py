from django.urls import path
from . import views
from backend.views import model_inference, model_codedata

urlpatterns = [
    path('inference/', model_inference),
    path('codedata/', model_codedata),
]

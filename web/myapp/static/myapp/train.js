function submitForm() {
    var form = document.getElementById('trainForm');
  
    var data = {
      epochs: form.epochs.value,
      attack: form.attack.value,
      method: form.method.value,
      trigger: form.trigger.value,
      target_label: form.target_label.value,
      poisoned_rate: form.poisoned_rate.value
    };
  
    fetch('/api/train', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(data),
    })
    .then(response => response.json())
    .then(data => {
      document.getElementById('result').innerHTML = JSON.stringify(data);
    })
    .catch((error) => {
      console.error('Error:', error);
    });
  }
  
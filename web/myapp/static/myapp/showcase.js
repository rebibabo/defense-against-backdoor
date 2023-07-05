document.getElementById('file-input').addEventListener('change', (e) => {
  const file = e.target.files[0];
  if (file) {
    const reader = new FileReader();
    reader.onload = (e) => {
      document.getElementById('person-text').value = e.target.result;
    };
    reader.readAsText(file);
  }
});

document.getElementById('confirm-input').addEventListener('click', () => {
  const inputText = document.getElementById('person-text').value;
  if (inputText) {
    // 使用 AJAX 发送请求
    fetch(`/process_input/?input_text=${encodeURIComponent(inputText)}`)
      .then((response) => response.json())
      .then((data) => {
        document.getElementById('person-text-output').innerText = data.normal_model_output;
        document.getElementById('attack1-text-output').innerText = data.attack1_model_output;
        document.getElementById('attack2-text-output').innerText = data.attack2_model_output;
        document.getElementById('attack3-text-output').innerText = data.attack3_model_output;
        document.getElementById('person-text-output3').innerText = data.defense_model_output;
      })
      .catch((error) => console.error('Error:', error));
  }
});



document.getElementById('toggle-display').addEventListener('click', function () {
  const inputText = document.getElementById('person-text').value;
  const scannedTextContainer = document.getElementById('scanned-text');
  const scannedText = scanner(inputText);

  if (scannedTextContainer.innerHTML === '') {
    scannedTextContainer.innerHTML = scannedText; // 修改这一行
  }

  const personText = document.getElementById('person-text');

  if (personText.style.display === 'none') {
    personText.style.display = 'block';
    scannedTextContainer.style.display = 'none';
  } else {
    personText.style.display = 'none';
    scannedTextContainer.style.display = 'block';
  }
});



// 实现scanner函数
function scanner(text) {
  let markedText = text.replace(/yzs/gi, '<span style="background-color: #07c160;">yzs</span>');
  return markedText;
}





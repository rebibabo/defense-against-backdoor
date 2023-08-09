// use.js

const useForm = document.getElementById('use-form');
const triggerSection = document.getElementById('trigger-section');
const codeContainer = document.getElementById('choose');
const pageFrame = document.getElementById('pageFrame');
const toggleDisplay = document.getElementById('toggle-display');

codeContainer.addEventListener('click', function (event) {
    event.preventDefault();
    const authorValue = document.getElementById('author').value;
    const triggerValue = document.getElementById('trigger').value;

    const pageURL = `http://114.55.247.221/static/clean/${authorValue}.html`;
    pageFrame.src = pageURL;

});
toggleDisplay.addEventListener('click', function (event) {
    event.preventDefault();
    const authorValue = document.getElementById('author').value;
    const triggerValue = document.getElementById('trigger').value;
   

    if(triggerValue!="clean"){
    const pageURL = `http://114.55.247.221/static/${triggerValue}/${authorValue}.html`;
    pageFrame.src = pageURL;}
    else{
    const pageURL = `http://114.55.247.221/static/clean/${authorValue}.html`;
    pageFrame.src = pageURL;
    }


});


useForm.addEventListener('submit',async function (event) {
    event.preventDefault();

    //const modelValue = document.getElementById('model').value;
    const authorValue = document.getElementById('author').value;
    const triggerValue = document.getElementById('trigger').value;

    try {
        await performFunction(1, "clean", authorValue, triggerValue);
        await performFunction(2, "invichar", authorValue, triggerValue);
        await performFunction(3, "tokensub", authorValue, triggerValue);
        await performFunction(4, "deadcode", authorValue, triggerValue);
        await performFunction(5, "invichar_d", authorValue, triggerValue);
        await performFunction(6, "tokensub_d", authorValue, triggerValue);
        await performFunction(7, "deadcode_d", authorValue, triggerValue);
    } catch (error) {
        console.error('Error:', error);
    }


});
async function performFunction(number, modelValue, authorValue, triggerValue) {
    const requestData = {
        "data":{model: modelValue,
        author: authorValue,
        trigger: triggerValue}
    };

    try {
        const response = await fetch('http://127.0.0.1:1000/model/inference/', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(requestData)
        });

        const data = await response.json();
        document.getElementById(`text-output${number}`).innerText = data.pred;


    } catch (error) {
        console.error(`Function ${number} - Error:`, error);
    }
}

// Show/hide trigger section based on user selection
const triggerYes = document.getElementById('trigger-yes');
const triggerNo = document.getElementById('trigger-no');

function updateTriggerSectionVisibility() {
    if (triggerYes.checked) {
        triggerSection.style.display = 'block';
        
      
    } else {
        triggerSection.style.display = 'none'; 
        document.getElementById('trigger').value='clean';
    }
}

triggerYes.addEventListener('change', updateTriggerSectionVisibility);
triggerNo.addEventListener('change', updateTriggerSectionVisibility);
// Call the function once on page load to initialize the visibility
updateTriggerSectionVisibility();




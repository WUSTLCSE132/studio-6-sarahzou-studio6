const int buttonPin = 2;

int buttonState;
int lastButtonState = LOW;

unsigned long lastDebounceTime = 0;
unsigned long debounceDelay = 200;

int count = 0;

void buttonPressed() {
  //Serial.println("Interrupt");
  count += 1;
  Serial.println(count);
}

void setup() {
  pinMode(buttonPin, INPUT_PULLUP);

  // Interrupts can happen on "edges" of signals.  
  // Three edge types are supported: CHANGE, RISING, and FALLING 
  attachInterrupt(digitalPinToInterrupt(buttonPin), buttonPressed, CHANGE);
  Serial.begin(9600);
}

void loop() {
  int reading = digitalRead(buttonPin);

  //detect change :-- start Timing
  if(reading != lastButtonState){
    lastDebounceTime = millis();
  }

  //time off :-- if truly changed, change buttonState
  if((millis() - lastDebounceTime) > debounceDelay){
    if(reading != buttonState){
      buttonState = reading;
      buttonPressed();
    }
  }

  //update lastButtonState every loop
  lastButtonState = reading;
  
//  Serial.println(count);
}

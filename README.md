# lp-sil-tcp-server-echo
IUT Nantes : LP SIL ~ Multithreaded TCP server with Echo protocol.

## Automatically
### Build & launch
This project can be construct with ant.

- Run the server : `ant run`

In addition you can use these commands :

- Clean the project : `ant clean`
- Compile the project : `ant clean compile`
- Generate Javadoc : `ant javadoc`

## Manually
### Build
- Move to `bin` folder : `cd lp-sil-tcp-server-echo/bin/`
- Use javac to compile the project : `javac .././src/app/Main.java -d ./ -cp ../src/`
- Create configuration file : `cp .env.example .env`
- Update your `.env` file to set your own configuration

### Launch
- Move to the project folder : `cd lp-sil-tcp-server-echo/`
- Execute Main class : `java -cp bin/ app.Main`

## Play with the parrot
![parrot gif](http://24.media.tumblr.com/a6faf44197197ac5b426a635208e2d69/tumblr_n18xtdrTCH1s02vreo1_400.gif)
- Establish a connection to the server : `telnet localhost 9900`
- Write something, then push `enter`
- If you are bored to play you can write `quit`

## TODO
- Socket inactivity delay with setSoTimeout : currently exception occurs with Scanner (write).
- Different strategies with the low level server implementation.

# lp-sil-tcp-server-echo
IUT Nantes : LP SIL ~ TCP (and UDP...) server with simple echo protocol

Etherpad (dev) : [TCP SERVER](https://beta.etherpad.org/p/tcp-server)

## Automatically
TODO

## Manually
### Build
- Move to `bin` folder : `cd lp-sil-tcp-server-echo/bin/`
- Use javac to compile the project : `javac .././src/app/Main.java -d ./ -cp ../src/`
- Create configuration file : `cp .env.example .env`
- Update your `.env` file to set your own configuration

### Launch
- Move to `bin` folder : `cd lp-sil-tcp-server-echo/bin/`
- Launch main app : `java app.Main`

## Play with the parrot
![parrot gif](http://24.media.tumblr.com/a6faf44197197ac5b426a635208e2d69/tumblr_n18xtdrTCH1s02vreo1_400.gif)
- Establish a connection to the server : `telnet localhost 9900`
- Write something, then push `enter`
- If you are bored to play you can write `quit`

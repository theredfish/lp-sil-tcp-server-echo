# lp-sil-tcp-server-echo
IUT Nantes : LP SIL ~ TCP (and UDP...) server with simple echo protocol

Etherpad (dev) : [TCP SERVER](https://beta.etherpad.org/p/tcp-server)

## Automatically
TODO

## Manually
### Build
- Move to `bin` folder : `cd lp-sil-tcp-server-echo/bin/`
- Use javac to compile the project : `javac .././src/app/Main.java -d ./ -cp ../src/`

### Launch
- Move to `bin` folder : `cd lp-sil-tcp-server-echo/bin/`
- Launch main app : `java app.Main`

## Play with the parrot
- Establish a connection to the server : `telnet localhost 9900`
- Write something, then push `enter`
- If you are bored to play you can write `quit`

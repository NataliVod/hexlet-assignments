package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Connected implements Connection {
    private TcpConnection tcpConnection;
    public Connected(TcpConnection tcpConnection) {
        this.tcpConnection = tcpConnection;
    }
    @Override
    public void connect() {
        System.out.println("Error! Connection already connected");
    }

    @Override
    public void disconnect() {
        TcpConnection tcpConnection = this.tcpConnection;
        tcpConnection.setConnection(new Disconnected(tcpConnection));
    }

    @Override
    public void write(String data) {

    }

    @Override
    public String toString() {
        return "connected";
    }
}
// END

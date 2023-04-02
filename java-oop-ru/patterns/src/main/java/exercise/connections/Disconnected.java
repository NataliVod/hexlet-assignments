package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Disconnected implements Connection {
    private TcpConnection tcpConnection;

    public Disconnected(TcpConnection tcpConnection) {
        this.tcpConnection = tcpConnection;
    }

    @Override
    public void connect() {
        TcpConnection tcpConnection = this.tcpConnection;
        tcpConnection.setConnection(new Connected(tcpConnection));
    }

    @Override
    public void disconnect() {
        System.out.println("Error! Connection already disconnected");
    }

    @Override
    public void write(String data) {
        System.out.println("Error! Can't write when disconnected");
    }

    @Override
    public String toString() {
        return "disconnected";
    }
}
// END

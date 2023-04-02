package exercise;
import exercise.connections.Connected;
import exercise.connections.Connection;
import exercise.connections.Disconnected;

import java.util.List;
import java.util.ArrayList;

// BEGIN
public class TcpConnection {
    private String address;
    private int port;
    private Connection connection;


    public TcpConnection(String address, int port) {
        this.address = address;
        this.port = port;
        this.connection = new Disconnected(this);
    }

    public String getCurrentState() {
        return this.connection.toString();
    }


    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    void connect() {
        this.connection.connect();
    }
    void disconnect() {
        this.connection.disconnect();
    }
    void write(String data) {
        this.connection.write(data);
    }

}


// END

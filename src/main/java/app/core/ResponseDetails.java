package app.core;

public class ResponseDetails<T> {

    private int statusCode;
    private T data;
    private byte[] rawData;

    public ResponseDetails(int statusCode, T data, byte[] rawData) {
        this.statusCode = statusCode;
        this.data = data;
        this.rawData = rawData;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public byte[] getRawData() {
        return rawData;
    }

    public void setRawData(byte[] rawData) {
        this.rawData = rawData;
    }
}

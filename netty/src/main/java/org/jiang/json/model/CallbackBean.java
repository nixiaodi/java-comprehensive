package org.jiang.json.model;

/**
 * @Description TODO
 * @Author jiang
 * @Create 2021/3/4
 * @Version 1.0
 */
public class CallbackBean {
    private String filename;
    private Float size;

    public CallbackBean() {
    }

    public CallbackBean(String filename, Float size) {
        this.filename = filename;
        this.size = size;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Float getSize() {
        return size;
    }

    public void setSize(Float size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "CallbackBean{" +
                "filename='" + filename + '\'' +
                ", size=" + size +
                '}';
    }
}

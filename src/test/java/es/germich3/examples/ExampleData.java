package es.germich3.examples;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"alias","x","y","z"})
public class ExampleData {

    private String alias;
    private int x, y, z;

    public String getAlias() {
        return alias;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setZ(int z) {
        this.z = z;
    }

	@Override
	public String toString() {
		return "ExampleData {alias=" + alias + ", x=" + x + ", y=" + y + ", z=" + z + "}";
	}

}

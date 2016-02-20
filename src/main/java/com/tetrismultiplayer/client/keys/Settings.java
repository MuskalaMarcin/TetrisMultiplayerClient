package main.java.com.tetrismultiplayer.client.keys;

import java.io.Serializable;

/**
 * Class representing users keys settings.
 */
public class Settings implements Serializable
{
    public String left, right, rotate, down, drop;
    public String ip;
    public Integer port;

    public Settings()
    {
	this.left = "Left";
	this.right = "Right";
	this.rotate = "Up";
	this.down = "Down";
	this.drop = "Space";
	this.ip = "127.0.0.1";
	this.port = 65534;
    }

    public String getDown()
    {
	return down;
    }

    public void setDown(String down)
    {
	this.down = down;
    }

    public String getDrop()
    {
	return drop;
    }

    public void setDrop(String drop)
    {
	this.drop = drop;
    }

    public String getIp()
    {
	return ip;
    }

    public void setIp(String ip)
    {
	this.ip = ip;
    }

    public String getLeft()
    {
	return left;
    }

    public void setLeft(String left)
    {
	this.left = left;
    }

    public Integer getPort()
    {
	return port;
    }

    public void setPort(Integer port)
    {
	this.port = port;
    }

    public String getRight()
    {
	return right;
    }

    public void setRight(String right)
    {
	this.right = right;
    }

    public String getRotate()
    {
	return rotate;
    }

    public void setRotate(String rotate)
    {
	this.rotate = rotate;
    }
}

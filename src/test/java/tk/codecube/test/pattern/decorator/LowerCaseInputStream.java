package tk.codecube.test.pattern.decorator;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class LowerCaseInputStream extends FilterInputStream {

	public LowerCaseInputStream(InputStream in) {
		super(in);
	}
	
	@Override
	public int read() throws IOException {
		int c = in.read();
		return c == -1 ? -1 : Character.toLowerCase(c);
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		int result = super.read(b, off, len);
		for(int i = off; i< off; ++i)
		{
			b[i] = (byte)Character.toLowerCase(result);
		}
		return result;
	}
	
	
}

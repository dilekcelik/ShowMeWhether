package dlk.clk.showmewhether;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WeatherXMLParsing extends Activity implements  android.view.View.OnClickListener{
	static final String baseURL = "http://www.google.com/ig/api?weather=";
	TextView tv;
	EditText city, state;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button b = (Button) findViewById(R.id.bWeather);
		city = (EditText) findViewById(R.id.etCity);
		state = (EditText) findViewById(R.id.etState);
		b.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String c = city.getText().toString();
		String s = state.getText().toString();
		
		StringBuilder URL = new StringBuilder(baseURL);
		URL.append(c+","+s);
		String fullUrl = URL.toString();
		
		try {
			URL website = new URL(fullUrl);
			//getting xmlreader to parse data
				SAXParserFactory spf = SAXParserFactory.newInstance();
				SAXParser sp = spf.newSAXParser();
				XMLReader xr = sp.getXMLReader();
				HandlingXMLStuff doingWork = new HandlingXMLStuff();
				xr.setContentHandler(doingWork);
				xr.parse(new InputSource(website.openStream()));
				
				String information = doingWork.getInformation();
				tv.setText(information);
				
		} catch (Exception e){
			tv.setText("Error!");
		}
	}

	
	

}

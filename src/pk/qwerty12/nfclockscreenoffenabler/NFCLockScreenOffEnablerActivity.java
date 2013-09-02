package pk.qwerty12.nfclockscreenoffenabler;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.CheckBox;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class NFCLockScreenOffEnablerActivity extends Activity {

	@SuppressLint("WorldReadableFiles")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nfclock_screen_off_enabler);

		final SharedPreferences prefs = getSharedPreferences(NFCLockScreenOffEnabler.PREFS, Context.MODE_WORLD_READABLE);
		if (!prefs.getBoolean(NFCLockScreenOffEnabler.PREF_LOCKED, true))
			((RadioButton) findViewById(R.id.radio1)).setChecked(true);

		((RadioGroup)findViewById(R.id.radioGroup1)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
		{
		    public void onCheckedChanged(RadioGroup rGroup, int checkedId)
		    {
		    	Editor prefsEditor = prefs.edit();
		    	prefsEditor.putBoolean(NFCLockScreenOffEnabler.PREF_LOCKED, checkedId == R.id.radio0);
		    	prefsEditor.commit();
		    }
		});

		//qlg 2013-08-09
		((CheckBox) findViewById(R.id.cbEnableTagLost)).setChecked(prefs.getBoolean(NFCLockScreenOffEnabler.PREF_TAGLOST, true));
		
		((CheckBox) findViewById(R.id.cbEnableTagLost)).setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener()
		{

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
		    	Editor prefsEditor = prefs.edit();
		    	prefsEditor.putBoolean(NFCLockScreenOffEnabler.PREF_TAGLOST, isChecked);
		    	prefsEditor.commit();
			}
			
		});

	}

}

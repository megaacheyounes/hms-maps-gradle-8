package com.younes.gradle8hmsmap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.fragment.app.FragmentActivity
import com.huawei.hms.maps.HuaweiMap
import com.huawei.hms.maps.MapsInitializer
import com.huawei.hms.maps.OnMapReadyCallback
import com.huawei.hms.maps.SupportMapFragment
import com.younes.gradle8hmsmap.databinding.MainActivityMapFragmentBinding
import com.younes.gradle8hmsmap.ui.theme.Gradle8HmsMapTheme


class MainActivity : FragmentActivity(), OnMapReadyCallback {

    var huaweiMap: HuaweiMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initMaps()

        setContent {
            Gradle8HmsMapTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(top=32.dp),
                            style = MaterialTheme.typography.headlineSmall,
                            text = "Huawei Maps Demo",
                        )
                        Text(
                            style = MaterialTheme.typography.bodyLarge,
                            text = "Gradle 8 (kts) + Compose",
                        )
                        HuaweiMapFragment(
                            Modifier.padding(top=32.dp),
                            onMapReadyCallback = this@MainActivity)
                    }

                }
            }
        }
    }

    fun initMaps() {
        MapsInitializer.initialize(this)
    }

    override fun onMapReady(huaweiMap: HuaweiMap?) {
        this.huaweiMap = huaweiMap
        this.huaweiMap?.uiSettings?.apply {
            isCompassEnabled = true
            isMyLocationButtonEnabled = true
            isZoomControlsEnabled = true
        }
    }


}

@Composable
fun HuaweiMapFragment(
    modifier:Modifier = Modifier,
    onMapReadyCallback: OnMapReadyCallback) {
    AndroidViewBinding(
        factory = MainActivityMapFragmentBinding::inflate,
        modifier =modifier ,
    ) {
        val mapFragment = mapfragmentMapfragmentdemo.getFragment<SupportMapFragment>()
        mapFragment.getMapAsync(onMapReadyCallback)
    }

}
package com.airconditioning;

import com.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ThermometherTest {

    @InjectMocks
    Thermomether thermomether;

    @Mock
    Sensor sensor;

    @Test
    void testWorkingSensor() {
        thermomether.setTemperature(25.0);
        when(sensor.isBlocked()).thenReturn(false);
        assertEquals(sensor, thermomether.getSensor());
        assertEquals(25.0, thermomether.getTemperature(), 0.001);
        verify(sensor, times(1)).isBlocked();
    }

    @Test
    void testBlockedSensor() {
        thermomether.setTemperature(25.0);
        when(sensor.isBlocked()).thenReturn(true);
        assertEquals(sensor, thermomether.getSensor());
        assertThrows(RuntimeException.class, () -> thermomether.getTemperature());
        verify(sensor, times(1)).isBlocked();
    }
}
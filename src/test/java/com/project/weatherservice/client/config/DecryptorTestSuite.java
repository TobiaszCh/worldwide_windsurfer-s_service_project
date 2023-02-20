package com.project.weatherservice.client.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DecryptorTestSuite {
    @Autowired
    private Decryptor decryptor;

    @Test
    public void testDecryptWithValidInput() {
        //Given
        String encrypted = "K~gsvrk&lux&zkyz";
        String expectedDecrypted = "Example for test";
        //When
        String actualDecrypted = decryptor.decrypt(encrypted);
        //Then
        assertEquals(expectedDecrypted, actualDecrypted);
    }

    @Test
    public void testDecryptWithEmptyInput() {
        //Given
        String encrypted = "";
        String expectedDecrypted = "";
        //When
        String actualDecrypted = decryptor.decrypt(encrypted);
        //Then
        assertEquals(expectedDecrypted, actualDecrypted);
    }

    @Test
    public void testDecryptWithSingleCharacterInput() {
        //Given
        String encrypted = "h";
        String expectedDecrypted = "b";
        //When
        String actualDecrypted = decryptor.decrypt(encrypted);
        //Then
        assertEquals(expectedDecrypted, actualDecrypted);
    }

    @Test
    public void testDecryptWithNullInput() {
        //Given && Then
        String encrypted= null;
        assertThrows(NullPointerException.class, () -> decryptor.decrypt(encrypted));
    }
}
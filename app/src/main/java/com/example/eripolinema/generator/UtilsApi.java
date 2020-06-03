package com.example.eripolinema.generator;

import com.example.eripolinema.service.PelaporsService;

public class UtilsApi {
    // 10.0.2.2 ini adalah localhost.
    // bisa juga di masukan dengan IP address kalian
    public static final String BASE_URL_API =
            "http://localhost:3000//";

    // Mendeklarasikan Interface BaseApiService
    public static PelaporsService getPelaporsService() {
        return ServiceGenerator.getClient(BASE_URL_API).create(PelaporsService.class);
    }
}

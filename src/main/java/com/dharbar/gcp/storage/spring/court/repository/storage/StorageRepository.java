package com.dharbar.gcp.storage.spring.court.repository.storage;

import com.dharbar.gcp.storage.spring.court.repository.storage.model.GcpFile;
import com.google.cloud.storage.Storage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.WritableResource;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StorageRepository {

    @Value("${bucket-name}")
    private String bucket;

    private ResourceLoader resourceLoader;
    private Storage storage;

    public StorageRepository(ResourceLoader resourceLoader, Storage storage) {
        this.resourceLoader = resourceLoader;
        this.storage = storage;
    }

    public List<GcpFile> getGcpFiles() {
        return StreamSupport.stream(storage.list(bucket).iterateAll().spliterator(), false)
                .map(GcpFile::of)
                .collect(Collectors.toList());
    }

    public Resource get(String name) {
        Resource resource = resourceLoader.getResource(createPath(name));
        if (!resource.exists()) {
            throw new IllegalArgumentException(String.format("file = %s not found", name));
        }

        return resource;
    }

    public void put(String name, byte[] bytes) throws IOException {
        Resource resource = resourceLoader.getResource(createPath(name));
        if (resource.exists()) {
            throw new IllegalArgumentException(String.format("file = %s already exist", name));
        }

        try (OutputStream os = ((WritableResource) resource).getOutputStream()) {
            os.write(bytes);
        }
    }


    private String createPath(String file) {
        return String.format("gs://%s/%s", bucket, file);
    }
}

package com.dharbar.gcp.storage.spring.court.repository.storage;

import com.dharbar.gcp.storage.spring.court.repository.storage.model.GcpFile;
import com.dharbar.gcp.storage.spring.court.service.mapper.GcpFileMapper;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.Storage.BlobTargetOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

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
			.map(GcpFileMapper::toClient)
			.collect(Collectors.toList());
	}

	public Resource get(String name) {
		Resource resource = resourceLoader.getResource(createPath(name));
		if (!resource.exists()) {
			throw new IllegalArgumentException(String.format("file = %s not found", name));
		}

		return resource;
	}

	public GcpFile put(String name, byte[] bytes, String contentType) {
		BlobInfo blobInfo = BlobInfo.newBuilder(bucket, name).setContentType(contentType).build();
		Blob blob = storage.create(blobInfo, bytes, BlobTargetOption.doesNotExist());
		return GcpFileMapper.toClient(blob);
	}

	private String createPath(String file) {
		return String.format("gs://%s/%s", bucket, file);
	}
}

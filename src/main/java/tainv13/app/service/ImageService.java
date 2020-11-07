package tainv13.app.service;

import java.util.List;

import tainv13.app.model.Image;

public abstract class ImageService {
	public abstract Image createImage(Image image);

	public abstract List<Image> findImageByMotelId(Long motelId);
	
	public abstract boolean multipleDeleteImage(List<Long> listImageId);
}

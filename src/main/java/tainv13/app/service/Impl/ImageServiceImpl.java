package tainv13.app.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tainv13.app.model.Image;
import tainv13.app.repository.ImageRepository;
import tainv13.app.service.ImageService;

@Service
public class ImageServiceImpl extends ImageService {

	@Autowired
	private ImageRepository imageRepository;

	@Override
	public Image createImage(Image image) {
		try {
			List<Image> imageByUrl = imageRepository.getImageByUrl(image.getUrl(), image.getMotelRoom().getMotelId());
			return imageByUrl.size() == 0 ? imageRepository.save(image) : null;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Image> findImageByMotelId(Long motelId) {
		return imageRepository.findImageByMotelId(motelId);
	}

	@Override
	public boolean multipleDeleteImage(List<Long> listImageId) {
		try {
			for (Long id : listImageId) {
				imageRepository.deleteById(id);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}

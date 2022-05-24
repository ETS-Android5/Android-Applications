public objectDetection(String foundObject) throws IOException, ModelException, TranslateException {
    BufferedImage img = BufferedImageUtils.fromVideoStream(foundObject);

    Criteria<BufferedImage, DetectedObjects> criteria =
            Criteria.builder()
                    .optApplication(Application.CV.OBJECT_DETECTION)
                    .setTypes(BufferedImage.class, DetectedObjects.class)
                    .optFilter("backbone", "resnet50")
                    .optProgress(new ProgressBar())
                    .build();

    try (ZooModel<BufferedImage, DetectedObjects> model = ModelZoo.loadModel(criteria)) {
        try (Predictor<BufferedImage, DetectedObjects> predictor = model.newPredictor()) {
            DetectedObjects detection = predictor.predict(img);
            System.out.println(detection);
        }
    }
}

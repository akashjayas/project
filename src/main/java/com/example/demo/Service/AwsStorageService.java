//package com.example.demo.Service;


//
//@Service
//public class StorageService {
//
//    private static final Logger logger = LoggerFactory.getLogger(StorageService.class);
//
//    @Value("${application.bucket.name}")
//    private String bucketName;
//
//    @Autowired
//    private AmazonS3 s3Client;
//    public URL uploadFile(String fileName, MultipartFile file) {
//        File objFile=convertMultipartFileToFile(file);
//        try {
//            AccessControlList acl=new AccessControlList();
//            acl.grantPermission(GroupGrantee.AllUsers,Permission.Read);
//            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName,objFile);
//            putObjectRequest.setAccessControlList(acl);
//            s3Client.putObject(putObjectRequest);
//            URL pictureUrl=new URL("https://"+bucketName+".s3.amazonaws.com/"+fileName);
//            objFile.delete();
//            return pictureUrl;
//        } catch (Exception e) {
//            logger.error("Error uploading file", e);
//            throw new RuntimeException("Error uploading file", e);
//        }
//
//    }
//
//    public void deleteFile(EventPicture eventPicture){
//        List<EventPicture.EventPictures> eventPictures=eventPicture.getEventPictures();
//        eventPictures.forEach(eventPictures1 -> deleteFile(eventPictures1.getEventPicture()));
//    }
//    public void deleteFile(SpotPicture spotPicture){
//        List<SpotPicture.SpotPictures> spotPicturesList=spotPicture.getSpotPicturesList();
//        spotPicturesList.forEach(spotPictures -> deleteFile(spotPictures.getSpotPicture()));
//    }
//    public String deleteFile(URL fileUrl) {
//        if(fileUrl!=null){
//            String fileName=fileUrl.getPath().substring(1);
//            System.out.println(fileName);
//            try {
//                s3Client.deleteObject(bucketName, fileName);
//                return "File " + fileName + " deleted.";
//            } catch (Exception e) {
//                logger.error("Error deleting file", e);
//                throw new RuntimeException("Error deleting file", e);
//            }
//        }else {
//            return "file name is empty";
//        }
//    }
//
//    private File convertMultipartFileToFile(MultipartFile file) {
//        File convertedFile = new File(file.getOriginalFilename());
//        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
//            fos.write(file.getBytes());
//        } catch (IOException e) {
//            logger.error("Error converting MultipartFile to File", e);
//            throw new RuntimeException("Error converting MultipartFile to File", e);
//        }
//        return convertedFile;
//    }
//}

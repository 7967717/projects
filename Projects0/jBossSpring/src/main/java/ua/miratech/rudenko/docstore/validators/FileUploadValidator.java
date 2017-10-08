package ua.miratech.rudenko.docstore.validators;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.miratech.rudenko.docstore.fileUploadDownload.UploadedFile;

/**
 * Created by RomanR on 1/29/14.
 */
@Component
public class FileUploadValidator implements Validator{

    public static final Logger LOG = Logger.getLogger("rootLogger");

    @Override
    public boolean supports(Class clazz) {
        //just validate the FileUpload instances
        return UploadedFile.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        LOG.info("entered validation");

        UploadedFile file = (UploadedFile) target;

        if(file.getFile().getSize()==0){
            LOG.info("got upload error");
            errors.rejectValue("file", "required.fileUpload");
        }
    }
}

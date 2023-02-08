/*
 * Copyright 2016-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.joinfaces.view;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.view.ViewScoped;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * FileMBean to test primefaces upload component.
 *
 * @author Marcelo Fernandes
 */
@SuppressFBWarnings("THROWS_METHOD_THROWS_RUNTIMEEXCEPTION")
@Component
@SessionScoped
public class FileMBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    private transient UploadedFile uploadedFile;

    @SuppressFBWarnings("EI_EXPOSE_REP")
    @Getter
    @Setter
    private transient UploadedFile uploadFile;

    @Getter
    private transient StreamedContent downloadFile;

    private transient String fileName;
    private transient String contentType;
    private transient byte[] contents;


    /**
     * Upload file action.
     */
    public void upload() {
        if (uploadFile != null) {
            this.uploadedFile = uploadFile;
            if (this.uploadedFile != null) {
                fileName = uploadedFile.getFileName();
                contentType = uploadedFile.getContentType();
                contents = uploadedFile.getContent();
                this.downloadFile = DefaultStreamedContent.builder()
                        .stream(() -> new ByteArrayInputStream(contents))
                        .contentType(contentType)
                        .name(fileName)
                        .build();
            }
        }

    }

}

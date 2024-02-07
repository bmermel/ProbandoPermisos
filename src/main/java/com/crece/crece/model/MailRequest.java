package com.crece.crece.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MailRequest {
        private List<String> mails;
        private MailStructure mailStructure;
        private String filePath;
}


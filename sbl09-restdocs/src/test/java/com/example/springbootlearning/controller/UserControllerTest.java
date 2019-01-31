package com.example.springbootlearning.controller;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.restdocs.headers.HeaderDocumentation;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test01_findAll() throws Exception {
        this.mockMvc
                .perform(
                        RestDocumentationRequestBuilders.get("/user", 1)
                                .header("token", "2E14D92B-1FB1-4D04-8EA3-486DA78914BA")
                )
                .andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document("1 获取所有用户",
                        Preprocessors.preprocessRequest(prettyPrint()),
                        Preprocessors.preprocessResponse(prettyPrint()),
                        HeaderDocumentation.requestHeaders(
                                HeaderDocumentation.headerWithName("token").description("访问令牌")
                        ),
                        PayloadDocumentation.responseFields(
                                PayloadDocumentation.fieldWithPath("[]").type(JsonFieldType.ARRAY).description("用户列表"),
                                PayloadDocumentation.fieldWithPath("[].id").description("ID"),
                                PayloadDocumentation.fieldWithPath("[].username").description("用户名"),
                                PayloadDocumentation.fieldWithPath("[].fullname").description("姓名"),
                                PayloadDocumentation.fieldWithPath("[].sex").description("性别"),
                                PayloadDocumentation.fieldWithPath("[].status").description("状态")
                        )
                        )
                );
    }

    @Test
    public void test02_getById() throws Exception {
        this.mockMvc
                .perform(
                        RestDocumentationRequestBuilders.get("/user/{id}", 1)
                                .header("token", "2E14D92B-1FB1-4D04-8EA3-486DA78914BA")
                )
                .andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document("2 根据ID获取指定用户",
                        Preprocessors.preprocessRequest(prettyPrint()),
                        Preprocessors.preprocessResponse(prettyPrint()),
                        HeaderDocumentation.requestHeaders(
                                HeaderDocumentation.headerWithName("token").description("访问令牌")
                        ),
                        PayloadDocumentation.responseFields(
                                PayloadDocumentation.fieldWithPath("id").description("ID"),
                                PayloadDocumentation.fieldWithPath("username").description("用户名"),
                                PayloadDocumentation.fieldWithPath("fullname").description("姓名"),
                                PayloadDocumentation.fieldWithPath("sex").description("性别"),
                                PayloadDocumentation.fieldWithPath("status").description("状态")
                        )
                        )
                );
    }

    @Test
    public void test03_add() throws Exception {
        this.mockMvc
                .perform(
                        RestDocumentationRequestBuilders.post("/user", 1)
                                .header("token", "2E14D92B-1FB1-4D04-8EA3-486DA78914BA")
                                .param("username", "lisi")
                                .param("fullname", "李四")
                                .param("sex", "1")
                                .param("status", "true")
                )
                .andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document("3 新增用户",
                        Preprocessors.preprocessRequest(prettyPrint()),
                        Preprocessors.preprocessResponse(prettyPrint()),
                        HeaderDocumentation.requestHeaders(
                                HeaderDocumentation.headerWithName("token").description("访问令牌")
                        ),
                        PayloadDocumentation.responseFields(
                                PayloadDocumentation.fieldWithPath("id").description("ID"),
                                PayloadDocumentation.fieldWithPath("username").description("用户名"),
                                PayloadDocumentation.fieldWithPath("fullname").description("姓名"),
                                PayloadDocumentation.fieldWithPath("sex").description("性别"),
                                PayloadDocumentation.fieldWithPath("status").description("状态")
                        )
                        )
                );
    }

    @Test
    public void test04_update() throws Exception {
        this.mockMvc
                .perform(
                        RestDocumentationRequestBuilders.put("/user/{id}", 1)
                                .header("token", "2E14D92B-1FB1-4D04-8EA3-486DA78914BA")
                                .param("username", "zhangsan")
                                .param("fullname", "张三")
                                .param("sex", "0")
                                .param("status", "true")
                )
                .andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document("4 根据ID更新指定用户",
                        Preprocessors.preprocessRequest(prettyPrint()),
                        Preprocessors.preprocessResponse(prettyPrint()),
                        HeaderDocumentation.requestHeaders(
                                HeaderDocumentation.headerWithName("token").description("访问令牌")
                        ),
                        RequestDocumentation.pathParameters(
                                RequestDocumentation.parameterWithName("id").description("ID")
                        ),
                        requestParameters(
                                parameterWithName("username").description("用户名"),
                                parameterWithName("fullname").description("姓名"),
                                parameterWithName("sex").description("性别, 0=女, 1=男"),
                                parameterWithName("status").description("状态, true=启用, false=禁用")
                        ),
                        PayloadDocumentation.responseBody()
                        )
                );
    }

    @Test
    public void test05_delete() throws Exception {
        this.mockMvc
                .perform(
                        RestDocumentationRequestBuilders.delete("/user/{id}", 1)
                                .header("token", "2E14D92B-1FB1-4D04-8EA3-486DA78914BA")
                )
                .andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document("5 根据ID删除指定用户",
                        Preprocessors.preprocessRequest(prettyPrint()),
                        Preprocessors.preprocessResponse(prettyPrint()),
                        HeaderDocumentation.requestHeaders(
                                HeaderDocumentation.headerWithName("token").description("访问令牌")
                        )
                        )
                );
    }

    @Test
    public void test99_adocBuild() throws IOException {
        String appDir = System.getProperty("user.dir");
        String adocPath = appDir + "/src/docs/asciidocs/apiList.adoc";
        StringBuilder content = new StringBuilder();

        Files.list(Paths.get(appDir + "/target/generated-snippets")).forEach(f -> {
            String apiName = f.getFileName().toString();
            content.append("=== " + apiName + "\n\n");
            this.fileAppend(content, f + "/request-headers.adoc", "request-headers 类型说明");
            this.fileAppend(content, f + "/http-request.adoc", "http-request");
            this.fileAppend(content, f + "/request-fields.adoc", "request-fields 类型说明");
            this.fileAppend(content, f + "/request-parameters.adoc", "request-parameters类型说明");
            this.fileAppend(content, f + "/request-body.adoc", "request-body示例");
            this.fileAppend(content, f + "/http-response.adoc", "http-response");
            this.fileAppend(content, f + "/response-fields.adoc", "response-fields 类型说明");
            this.fileAppend(content, f + "/response-body.adoc", "request-body示例");
        });
        Files.createDirectories(Paths.get(adocPath).getParent());
        Files.write(Paths.get(adocPath), content.toString().getBytes(), StandardOpenOption.CREATE);
    }

    private void fileAppend(StringBuilder stringBuilder, String path, String title) {
        if (Files.exists(Paths.get(path))) {
            stringBuilder.append("==== " + title + " \n\n");
            stringBuilder.append("include::" + path + "[]" + "\n\n");
        }
    }
}
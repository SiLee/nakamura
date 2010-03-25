/*
 * Licensed to the Sakai Foundation (SF) under one or more contributor license agreements.
 * See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The SF licenses this file to you under the Apache License, Version
 * 2.0 (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under
 * the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package org.sakaiproject.nakamura.docproxy.url;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class UrlDocumentResultTest {
  @Test
  public void testDefaultConstructor() {
    UrlDocumentResult result = new UrlDocumentResult();
    assertEquals(null, result.getUri());
    assertEquals(null, result.getContentType());
    assertEquals(0, result.getContentLength());
  }

  @Test
  public void testAltConstructor() {
    String uri = "http://localhost/file";
    String contentType = "text/plain";
    long contentLength = uri.length();
    HashMap<String, Object> props = new HashMap<String, Object>();
    props.put("key1", "value1");
    props.put("key2", "value2");
    UrlDocumentResult result = new UrlDocumentResult(uri, contentType, contentLength, props);

    assertEquals(uri, result.getUri());
    assertEquals(contentType, result.getContentType());
    assertEquals(contentLength, result.getContentLength());
    assertEquals(props, result.getProperties());
  }

  @Test
  public void testAltConstructorNoProps() {
    String uri = "http://localhost/file";
    String contentType = "text/plain";
    long contentLength = uri.length();

    UrlDocumentResult result = new UrlDocumentResult(uri, contentType, contentLength, null);
    assertEquals(uri, result.getUri());
    assertEquals(contentType, result.getContentType());
    assertEquals(contentLength, result.getContentLength());
  }

  @Test
  public void testAddProperties() {
    UrlDocumentResult result = new UrlDocumentResult();
    result.setProperties(null);

    result.addProperty("key1", "value1");
    result.addProperty("key2", "value2");
    result.addProperty("key3", "value3");

    Map<String, Object> props = result.getProperties();
    assertTrue(props.containsKey("key1"));
    assertEquals("value1", props.get("key1"));
    assertTrue(props.containsKey("key2"));
    assertEquals("value2", props.get("key2"));
    assertTrue(props.containsKey("key3"));
    assertEquals("value3", props.get("key3"));
  }

  @Test
  public void testType() {
    UrlDocumentResult result = new UrlDocumentResult();
    assertEquals(UrlRepositoryProcessor.TYPE, result.getType());
  }

  @Test
  public void testEquals() {
    UrlDocumentResult result = new UrlDocumentResult();

    assertFalse(result.equals(null));
    assertTrue(result.equals(result));
    assertFalse(result.equals(this));

  }
  
  @Test
  public void testEqualsContent() {
    UrlDocumentResult result1 = new UrlDocumentResult();
    UrlDocumentResult result2 = new UrlDocumentResult();

    result2.setContentLength(100);
    assertFalse(result1.equals(result2));

    result2.setContentLength(result1.getContentLength());
    result2.setContentType("other");
    assertFalse(result1.equals(result2));
    result1.setContentType("what");
    assertFalse(result1.equals(result2));

    result2.setContentType(result1.getContentType());
    result2.setProperties(null);
    assertFalse(result1.equals(result2));
    assertFalse(result2.equals(result1));

    result2.setProperties(result1.getProperties());
    result2.setUri("other");
    assertFalse(result1.equals(result2));
    assertFalse(result2.equals(result1));
  }
}

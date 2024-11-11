import axios from "axios";
import { useEffect, useState } from "react";

const Blog = () => {
  const [blogData, setBlogData] = useState([]);
  const [loading, setLoading] = useState("loading..");
  const [successRes, setSuccessRes] = useState(false);
  const [errorVisible, setErrorVisible] = useState(false);
  const [errorMsg, setErrorMsg] = useState();
  const [nothingMsg, setNothingMsg] = useState("No blog posts were shared.");
  const [checkNothing, setCheckNothing] = useState(false);

  useEffect(() => {
    setSuccessRes(true);
    axios
      .get("http://localhost:8080/content/public/contents/published")
      .then((res) => {
        if (res.data.data.length === 0) {
          setCheckNothing(true);
        } else {
          setBlogData(res.data.data);
          setSuccessRes(false);
          setCheckNothing(false);
        }
      })
      .catch((res) => {
        setErrorMsg(res.message);
        setErrorVisible(true);
        setSuccessRes(false);
      });
  }, []);

  return (
    <>
      <section className="blog">
        {successRes && <h1>{loading}</h1>}
        {errorVisible && <h1>{errorMsg}</h1>}
        {blogData.map((blog) => {
          return (
            <div className="blog-content">
              {checkNothing && <h1>{nothingMsg}</h1>}
              <h1>{blog.contentTitle}</h1>
              <p>{blog.contentDescription}</p>
              <p className="publis-p">
                <span>Publish Date:</span>
                <span>{blog.publishDate}</span>
              </p>
            </div>
          );
        })}
      </section>
    </>
  );
};
export default Blog;

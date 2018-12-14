/*
 * JAVE - A Java Audio/Video Encoder (based on FFMPEG)
 * 
 * Copyright (C) 2008-2009 Carlo Pelliccia (www.sauronsoftware.it)
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package it.sauronsoftware.jave;

import java.io.Serializable;

/**
 * Attributes controlling the video encoding process.
 * 
 * @author Carlo Pelliccia
 */
public class VideoAttributes implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * This value can be setted in the codec field to perform a direct stream
	 * copy, without re-encoding of the audio stream.
	 */
	public static final String DIRECT_STREAM_COPY = "copy";

	/**
	 * The codec name for the encoding process. If null or not specified the
	 * encoder will perform a direct stream copy.
	 */
	private String codec = null;

	/**
	 * The the forced tag/fourcc value for the video stream.
	 */
	private String tag = null;

	/**
	 * The bitrate value for the encoding process. If null or not specified a
	 * default value will be picked.
	 */
	private Integer bitRate = null;

	/**
	 * The frame rate value for the encoding process. If null or not specified a
	 * default value will be picked.
	 */
	private Integer frameRate = null;

	/**
	 * The video size for the encoding process. If null or not specified the
	 * source video size will not be modified.
	 */
	private VideoSize size = null;

	/**
	 * Returns the codec name for the encoding process.
	 * 
	 * @return The codec name for the encoding process.
	 */
	String getCodec() {
		return codec;
	}

	/**
	 * Sets the codec name for the encoding process. If null or not specified
	 * the encoder will perform a direct stream copy.
	 * 
	 * Be sure the supplied codec name is in the list returned by
	 * {@link Encoder#getVideoEncoders()}.
	 * 
	 * A special value can be picked from
	 * {@link VideoAttributes#DIRECT_STREAM_COPY}.
	 * 
	 * @param codec
	 *            The codec name for the encoding process.
	 */
	public void setCodec(String codec) {
		this.codec = codec;
	}

	/**
	 * Returns the the forced tag/fourcc value for the video stream.
	 * 
	 * @return The the forced tag/fourcc value for the video stream.
	 */
	String getTag() {
		return tag;
	}

	/**
	 * Sets the forced tag/fourcc value for the video stream.
	 * 
	 * @param tag
	 *            The the forced tag/fourcc value for the video stream.
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}

	/**
	 * Returns the bitrate value for the encoding process.
	 * 
	 * @return The bitrate value for the encoding process.
	 */
	Integer getBitRate() {
		return bitRate;
	}

	/**
	 * Sets the bitrate value for the encoding process. If null or not specified
	 * a default value will be picked.
	 * 
	 * @param bitRate
	 *            The bitrate value for the encoding process.
	 */
	public void setBitRate(Integer bitRate) {
		this.bitRate = bitRate;
	}

	/**
	 * Returns the frame rate value for the encoding process.
	 * 
	 * @return The frame rate value for the encoding process.
	 */
	Integer getFrameRate() {
		return frameRate;
	}

	/**
	 * Sets the frame rate value for the encoding process. If null or not
	 * specified a default value will be picked.
	 * 
	 * @param frameRate
	 *            The frame rate value for the encoding process.
	 */
	public void setFrameRate(Integer frameRate) {
		this.frameRate = frameRate;
	}

	/**
	 * Returns the video size for the encoding process.
	 * 
	 * @return The video size for the encoding process.
	 */
	VideoSize getSize() {
		return size;
	}

	/**
	 * Sets the video size for the encoding process. If null or not specified
	 * the source video size will not be modified.
	 * 
	 * @param size
	 *            he video size for the encoding process.
	 */
	public void setSize(VideoSize size) {
		this.size = size;
	}

	public String toString() {
		return getClass().getName() + "(codec=" + codec + ", bitRate="
				+ bitRate + ", frameRate=" + frameRate + ", size=" + size + ")";
	}

}
